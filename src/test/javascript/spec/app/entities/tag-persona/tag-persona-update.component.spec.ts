import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TagPersonaUpdateComponent } from 'app/entities/tag-persona/tag-persona-update.component';
import { TagPersonaService } from 'app/entities/tag-persona/tag-persona.service';
import { TagPersona } from 'app/shared/model/tag-persona.model';

describe('Component Tests', () => {
  describe('TagPersona Management Update Component', () => {
    let comp: TagPersonaUpdateComponent;
    let fixture: ComponentFixture<TagPersonaUpdateComponent>;
    let service: TagPersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TagPersonaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TagPersonaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TagPersonaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TagPersonaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TagPersona(123);
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
        const entity = new TagPersona();
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
