import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitatoUpdateComponent } from 'app/entities/invitato/invitato-update.component';
import { InvitatoService } from 'app/entities/invitato/invitato.service';
import { Invitato } from 'app/shared/model/invitato.model';

describe('Component Tests', () => {
  describe('Invitato Management Update Component', () => {
    let comp: InvitatoUpdateComponent;
    let fixture: ComponentFixture<InvitatoUpdateComponent>;
    let service: InvitatoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitatoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InvitatoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InvitatoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InvitatoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Invitato(123);
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
        const entity = new Invitato();
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
