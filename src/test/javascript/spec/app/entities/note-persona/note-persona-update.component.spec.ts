import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { NotePersonaUpdateComponent } from 'app/entities/note-persona/note-persona-update.component';
import { NotePersonaService } from 'app/entities/note-persona/note-persona.service';
import { NotePersona } from 'app/shared/model/note-persona.model';

describe('Component Tests', () => {
  describe('NotePersona Management Update Component', () => {
    let comp: NotePersonaUpdateComponent;
    let fixture: ComponentFixture<NotePersonaUpdateComponent>;
    let service: NotePersonaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [NotePersonaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NotePersonaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NotePersonaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NotePersonaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NotePersona(123);
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
        const entity = new NotePersona();
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
