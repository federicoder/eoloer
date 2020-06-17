import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { EmailPersonaUpdateComponent } from 'app/entities/email-persona/email-persona-update.component';
import { EmailPersonaService } from 'app/entities/email-persona/email-persona.service';
import { EmailPersona } from 'app/shared/model/email-persona.model';

describe('Component Tests', () => {
  describe('EmailPersona Management Update Component', () => {
    let comp: EmailPersonaUpdateComponent;
    let fixture: ComponentFixture<EmailPersonaUpdateComponent>;
    let service: EmailPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [EmailPersonaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EmailPersonaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EmailPersonaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EmailPersonaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EmailPersona(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EmailPersona();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
